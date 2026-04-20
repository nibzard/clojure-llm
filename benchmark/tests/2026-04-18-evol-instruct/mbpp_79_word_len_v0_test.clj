(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-odd-length-words ["cat" "no" "hello" "a"])))
  (is (= 0 (count-odd-length-words [])))
  (is (= 0 (count-odd-length-words nil))))

(run-test test-variation)
