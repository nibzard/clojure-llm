(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["level" 0] (find_palindrome_position "level up the radar"))))

(run-test test-variation)
