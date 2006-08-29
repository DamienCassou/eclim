/**
 * Copyright (c) 2005 - 2006
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclim.command;

import java.util.Map;

import org.eclim.preference.Preferences;

/**
 * Abstract implmentation of {@link Command}.
 *
 * @author Eric Van Dewoestine (ervandew@yahoo.com)
 * @version $Revision$
 */
public abstract class AbstractCommand
  implements Command
{
  private static final String VIM_FILTER = "vim";

  private Map filters;

  /**
   * {@inheritDoc}
   */
  public OutputFilter getFilter (String _name)
  {
    if(filters != null){
      return (OutputFilter)filters.get(_name);
    }
    return null;
  }

  /**
   * Set filters.
   * <p/>
   * Dependency injection.
   *
   * @param _filters the value to set.
   */
  public void setFilters (Map _filters)
  {
    this.filters = _filters;
  }

  /**
   * If a filter name was provided in the CommandLine, then an attempt will be
   * made to locate the filter and run the result through it.
   * <p/>
   * When no filter name is supplied in the command line, "vim" will be used as
   * the default.
   *
   * @param _result The pre-filtered result.
   * @return The post-filtered result.
   */
  protected Object filter (CommandLine _commandLine, Object _result)
    throws Exception
  {
    String filterName = _commandLine.getValue(Options.FILTER_OPTION);
    if(filterName == null){
      filterName = VIM_FILTER;
    }

    OutputFilter filter = getFilter(filterName);
    if(filter != null){
      return filter.filter(_commandLine, _result);
    }
    return _result;
  }

  /**
   * Gets the preferences.
   *
   * @return Preferences.
   */
  public Preferences getPreferences ()
  {
    return Preferences.getInstance();
  }
}
