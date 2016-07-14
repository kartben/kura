/*******************************************************************************
 * Copyright (c) 2015 Red Hat Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann <jreimann@redhat.com> - initial API and implementation
 *******************************************************************************/
 
import groovy.io.FileType

def systemBundle = "org.eclipse.osgi"

def sl = new Properties()
sl.load (new File('src/main/resources/startlevels.properties').newReader());

def s = ""
def dir = new File("target/instance/plugins")
def first = true

dir.eachFileRecurse (FileType.FILES) { file ->
  if ( !first ) { s += "," }
  
  def basename = file.name.replaceAll('_.*\\.jar$', '')
  def bsl = sl.getProperty ( basename, "5:start" );
  
  if ( basename != systemBundle ) {
  	s += "reference:file:"  + file.name + "@" + bsl
  }
  	
  first = false
}

def props = new Properties()
props.setProperty('osgi.bundles', s)
props.store(new File('target/bundles.properties').newWriter(), null)
