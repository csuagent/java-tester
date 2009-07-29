/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JdbcProxy.
 * 
 * The Initial Developer of the Original Code is Frans van Gool.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * Frans van Gool. All Rights Reserved.
 * 
 * Contributor(s): Frans van Gool.
 * 
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU Lesser General Public License (the “LGPL License”), in which case the
 * provisions of LGPL License are applicable instead of those above. If you wish
 * to allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under the MPL,
 * indicate your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do not
 * delete the provisions above, a recipient may use your version of this file
 * under either the MPL or the LGPL License.
 */
package nl.griffelservices.proxy.stub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This tracer logs the stub information to files in a folder,
 * or to System.out if no folder has been specified.
 * 
 * @author Frans van Gool
 */
public class FolderStubTracer implements StubTracer
{
  /** the request encoder */
  private static final RequestEncoder requestEncoder = new RequestEncoder();
  /** the response encoder */
  private static final ResponseEncoder responseEncoder = new ResponseEncoder();
  
  /** the folder to save the stub information to */
  private File folder;
  
  /**
   * Constructs a FolderStubTracer object.
   * 
   * @param folder the folder to save the stub information to
   */
  public FolderStubTracer(File folder)
  {
    this.folder = folder;
  }

  public void trace(Request request, Response response) throws Exception
  {
    String id = request.getDesiredId();
    String status = request.getDesiredStatus().getDesiredValue();
    save("request_" + id + "_" + status + ".txt", requestEncoder.encode(request));
    save("response_" + id + "_" + status + ".txt", responseEncoder.encode(response));
  }

  /**
   * Saves the content to a file if {@link #folder} is not <code>null</code> and to <code>System.out</code> otherwise.
   * 
   * @param filename the name of the file to save to
   * @param content the content to save
   * @throws IOException if an error occurs
   */
  private void save(String filename, String content) throws IOException
  {
    if (folder == null)
    {
      System.out.println(content);
    }
    else
    {
      File file = new File(folder, filename);
      FileWriter fw = new FileWriter(file);
      fw.write(content);
      fw.close();
    }
  }
}
