/**
 * Copyright (C) 2011  Eric Van Dewoestine
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.eclim.plugin.sdt.project;

import org.eclim.plugin.core.project.ProjectManager;

import org.eclim.plugin.jdt.project.JavaProjectManager;

import org.eclim.plugin.sdt.PluginResources;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;

import org.eclipse.core.runtime.NullProgressMonitor;

import scala.tools.eclipse.Nature;

/**
 * {@link ProjectManager} for scala projects.
 *
 * @author Eric Van Dewoestine
 */
public class ScalaProjectManager
  extends JavaProjectManager
{

  /**
   * {@inheritDoc}
   * @see JavaProjectManager#create(IProject,String)
   */
  @Override
  protected void create(IProject project, String depends)
    throws Exception
  {
    super.create(project, depends);

    IProjectDescription desc = project.getDescription();
    String[] natures = desc.getNatureIds();
    String[] newNatures = new String[natures.length + 1];
    System.arraycopy(natures, 0, newNatures, 0, natures.length);
    newNatures[natures.length] = PluginResources.NATURE;
    desc.setNatureIds(newNatures);
    project.setDescription(desc, new NullProgressMonitor());

    Nature nature = new Nature();
    nature.setProject(project);
    nature.configure();
  }
}
