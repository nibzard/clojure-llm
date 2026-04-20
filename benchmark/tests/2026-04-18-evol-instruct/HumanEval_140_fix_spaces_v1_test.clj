(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" :_ "b"] (normalize-gaps ["a" nil "b"]))))

(run-test test-variation)
