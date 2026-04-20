(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("a" "bb" "dd" "ccc")
         (stable-sort-by count ["bb" "a" "ccc" "dd"])))
  (is (= '({:name "B" :age 1} {:name "A" :age 2} {:name "C" :age 2})
         (stable-sort-by :age [{:name "A" :age 2}
                               {:name "B" :age 1}
                               {:name "C" :age 2}])))
  (is (= '() (stable-sort-by identity nil))))

(run-test test-variation)
