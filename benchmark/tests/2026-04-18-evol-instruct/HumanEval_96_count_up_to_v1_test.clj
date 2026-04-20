(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(2 3) (take 2 (count_up_to 5))))
  (is (= '(2 3 5 7) (take 4 (count_up_to 11))))
  (is (= '() (count_up_to 0)))
  (is (= '(2 3 5 7 11 13 17 19) (take 8 (count_up_to 20)))))

(run-test test-variation)
