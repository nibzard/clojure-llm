(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a,b,c" (concatenate_nonblank ["a" nil "" "b" "  " "c"] ","))))

(run-test test-variation)
