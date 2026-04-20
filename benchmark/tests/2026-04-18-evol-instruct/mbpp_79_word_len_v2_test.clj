(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-odd-length-words ["cat" "" nil "dogs" "go"]))))

(run-test test-variation)
