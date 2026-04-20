(defn recursive_list_product
  "Return the product of all numeric elements in a nested list or vector.

  Ignore nil values, and treat empty collections as having product 1.

  Examples:
  (recursive_list_product [1 [2 nil] '(3 [4])]) ;=> 24
  (recursive_list_product [])                   ;=> 1
  (recursive_list_product [2 [0 5]])            ;=> 0"
  [data]
  (letfn [(step [x]
            (cond
              (nil? x) 1
              (sequential? x) (reduce * 1 (map step x))
              :else x))]
    (step data)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 24 (recursive_list_product [1 [2 nil] '(3 [4])])))
  (is (= 1 (recursive_list_product [])))
  (is (= 0 (recursive_list_product [2 [0 5]]))))

(run-test test-variation)
