/*
 * Copyright 2004 - 2012 Mirko Nasato and contributors
 *           2016 - 2017 Simon Braconnier and contributors
 *
 * This file is part of JODConverter - Java OpenDocument Converter.
 *
 * JODConverter is an Open Source software: you can redistribute it and/or
 * modify it under the terms of either (at your option) of the following
 * licenses:
 *
 * 1. The GNU Lesser General Public License v3 (or later)
 *    http://www.gnu.org/licenses/lgpl-3.0.txt
 * 2. The Apache License, Version 2.0
 *    http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package org.jodconverter.office;

import static org.jodconverter.office.OfficeUtils.toUrl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.Test;

public class OfficeUtilsTest {

  /** Tests the OfficeUtils.toUrl function on unix OS. */
  @Test
  public void unixToUrl() {

    assumeTrue(SystemUtils.IS_OS_UNIX);

    assertEquals(toUrl(new File("/tmp/document.odt")), "file:///tmp/document.odt");
    assertEquals(
        toUrl(new File("/tmp/document with spaces.odt")),
        "file:///tmp/document%20with%20spaces.odt");
  }

  /** Tests the OfficeUtils.toUrl function on Windows OS. */
  @Test
  public void windowsToUrl() {

    assumeTrue(SystemUtils.IS_OS_WINDOWS);

    String tempDir = System.getProperty("java.io.tmpdir");
    final File tempDirFile = new File(tempDir);
    tempDir = FilenameUtils.normalizeNoEndSeparator(tempDir, true);

    assertEquals(
        toUrl(new File(tempDirFile, "document.odt")), "file:///" + tempDir + "/document.odt");
    assertEquals(
        toUrl(new File(tempDirFile, "document with spaces.odt")),
        "file:///" + tempDir + "/document%20with%20spaces.odt");
  }
}