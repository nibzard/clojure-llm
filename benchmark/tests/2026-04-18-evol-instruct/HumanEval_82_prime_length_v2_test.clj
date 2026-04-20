(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["b" "c" "e"] (prime-index-filter ["a" "b" "c" "d" "e" "f"])))
  (is (= '(20 30 50 70) (prime-index-filter '(10 20 30 40 50 60 70))))
  (is (= '() (prime-index-filter []))))

(run-test test-variation)
