(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '({:name "b" :price 30} {:name "c" :price 20})
         (top-n-by :price [{:name "a" :price 10}
                           {:name "b" :price 30}
                           {:name "c" :price 20}] 2)))
  (is (= '("bbb")
         (top-n-by count ["a" "bbb" "cc"] 1)))
  (is (= '()
         (top-n-by :price [{:name "a" :price 10}] 0))))

(run-test test-variation)
