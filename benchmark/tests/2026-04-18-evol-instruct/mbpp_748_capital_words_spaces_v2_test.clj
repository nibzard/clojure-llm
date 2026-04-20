(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:user :id :number] (split-camel-keys "userIDNumber"))))

(run-test test-variation)
