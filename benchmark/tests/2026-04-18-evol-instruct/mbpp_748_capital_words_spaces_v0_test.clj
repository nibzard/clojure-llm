(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "Hello World" (split-camel-words "HelloWorld")))
  (is (= "XML Http Request" (split-camel-words "XMLHttpRequest")))
  (is (= nil (split-camel-words nil))))

(run-test test-variation)
