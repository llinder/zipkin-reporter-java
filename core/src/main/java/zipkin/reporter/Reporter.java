/**
 * Copyright 2016 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package zipkin.reporter;

import zipkin.Span;

import static zipkin.internal.Util.UTF_8;

/**
 * Spans are created in instrumentation, transported out-of-band, and eventually persisted.
 * Reporters sends spans (or encoded spans) recorded by instrumentation out of process.
 *
 * <S>Type of span to report, usually {@link zipkin.Span}, but extracted for reporting other java
 * types like HTrace spans to zipkin, and to allow future Zipkin model types to be reported (ex.
 * zipkin2.Span).
 */
public interface Reporter<S> {
  Reporter<Span> CONSOLE = s -> System.out.println(new String(Encoder.JSON.encode(s), UTF_8));

  /**
   * Schedules the span to be sent onto the transport.
   *
   * @param span Span, should not be <code>null</code>.
   */
  void report(S span);
}
