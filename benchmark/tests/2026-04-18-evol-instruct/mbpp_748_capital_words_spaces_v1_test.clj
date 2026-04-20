(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "camel Case Words" (split-camel-words "camelCaseWords"))))

(run-test test-variation)
