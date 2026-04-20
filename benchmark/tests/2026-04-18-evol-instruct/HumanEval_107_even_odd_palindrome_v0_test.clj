(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 1] (count-palindromic-strings ["aa" "abc" nil "racecar" "" "noon"]))))

(run-test test-variation)
