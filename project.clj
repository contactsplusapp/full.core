(defproject fullcontact/full.core "1.1.3"
  :description "FullContact's core Clojure(Script) library - logging, configuration and sugar."
  :url "https://github.com/contactsplusapp/full.core"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}
  :repositories [["fullcontact" {:url "https://contactsplus.jfrog.io/artifactory/repo"}]
                 ["releases" {:url "https://contactsplus.jfrog.io/artifactory/libs-release-local"}]
                 ["snapshots" {:url "https://contactsplus.jfrog.io/artifactory/libs-snapshot-local"}]]
  :deploy-repositories [["releases" {:url "https://contactsplus.jfrog.io/artifactory/libs-release-local" :sign-releases false}]
                        ["snapshots" {:url "https://contactsplus.jfrog.io/artifactory/libs-snapshot-local" :sign-releases false}]]
  :managed-dependencies [[org.clojure/tools.namespace "0.3.0"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-api "1.7.21"]
                 [org.slf4j/jul-to-slf4j "1.7.21"]
                 [me.moocar/logback-gelf "0.3" :exclusions [org.slf4j/slf4j-api]]
                 [ch.qos.logback/logback-classic "1.1.7" :exclusions [org.slf4j/slf4j-api]]
                 [clj-yaml "0.4.0" :exclusions [org.yaml/snakeyaml]]
                 [org.yaml/snakeyaml "1.17"]
                 [clj-time "0.15.0"]
                 [ns-tracker "0.4.0"]
                 [commons-codec/commons-codec "1.10"]]
  :aliases {"at" ["test-refresh"]
            "ats" ["doo" "phantom"]}
  :aot [clojure.tools.logging.impl]
  :cljsbuild {:builds {:test {:source-paths ["src" "test"]
                              :compiler {:output-to "target/test.js"
                                         :main 'full.core.test-runner
                                         :optimizations :simple
                                         :pretty-print true}}}}
  :doo {:build "test"}
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.15.0"]
                             [lein-cljsbuild "1.1.4"]
                             [lein-doo "0.1.7"]]}})
