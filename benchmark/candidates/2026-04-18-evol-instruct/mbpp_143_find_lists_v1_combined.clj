(defn count-nested-lists
  "Return the number of list values found anywhere in a nested collection.
  
  Examples:
  (count-nested-lists '(1 (2 3) [4 (5)] nil))
  => 2
  
  (count-nested-lists nil)
  => 0
  
  The function should count only values that satisfy list?, and it should
  work for nested vectors, maps, and lazy sequences."
  [x]
  (letfn [(step [v]
            (cond
              (nil? v) 0
              (list? v) (+ 1 (reduce + 0 (map step v)))
              (map? v) (reduce + 0 (concat (map step (keys v))
                                           (map step (vals v))))
              (coll? v) (reduce + 0 (map step v))
              :else 0))]
    (step x)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-nested-lists '(1 (2 3) [4 (5)] nil))))
  (is (= 0 (count-nested-lists nil))))

(run-test test-variation)
