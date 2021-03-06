/*
 * Sonar SCM Stats Plugin
 * Copyright (C) 2012 Patroklos PAPAPETROU
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.sonar.plugins.scmstats.measures;

import java.util.HashMap;
import java.util.Map;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.PropertiesBuilder;

public abstract class AbstractScmStatsMeasure {

  private Measure measure;
  private final Map<String, Integer> dataMap = new HashMap<String, Integer>();
  private final SensorContext context;

  public AbstractScmStatsMeasure(final Metric metric, final Map<String, Integer> map,
          final SensorContext context) {
    this.init();
    this.context = context;
    getDataMap().putAll(map);
    measure = new PropertiesBuilder<String, Integer>(metric).addAll(dataMap).build();
  }

  public void save() {
    context.saveMeasure(getMeasure());
  }

  public Measure getMeasure() {
    return measure;
  }

  protected final Map<String, Integer> getDataMap() {
    return dataMap;
  }
  protected abstract void init();
}