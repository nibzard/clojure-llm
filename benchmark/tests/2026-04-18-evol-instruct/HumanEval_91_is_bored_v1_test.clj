(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-starting-is ["Hello world" "I like Clojure" "  I am here" "This is fine"])))
  (is (= 0 (count-starting-is [])))
  (is (= 1 (count-starting-is [nil "" "  !" "I."]))))

(run-test test-variation)
