(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 24 (recursive_list_product [1 [2 nil] '(3 [4])])))
  (is (= 1 (recursive_list_product [])))
  (is (= 0 (recursive_list_product [2 [0 5]]))))

(run-test test-variation)
