(defproject ports-and-adapters "0.1.0-SNAPSHOT"
  :description "Examples of ports-and-adapters implementations"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [orchestra "2021.01.01-1"]]
  :profiles {:dev {:dependencies [[lambdaisland/kaocha "1.67.1055"]
                                  [org.clojure/test.check "0.9.0"]]}}
  :aliases {"kaocha" ["run" "-m" "kaocha.runner"]}
  :repl-options {:init-ns ports-and-adapters.core})
