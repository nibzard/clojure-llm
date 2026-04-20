(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [{:name "B" :age 20} {:name "A" :age 30}]
         (sort-by-keywords :age [{:name "A" :age 30} {:name "B" :age 20}])))
  (is (= [{:id 2 :score 10} {:id 1}]
         (sort-by-keywords :score [{:id 1} {:id 2 :score 10}])))
  (is (= [{:n 1 :v 1} {:n 3 :v 2} {:n 2}]
         (sort-by-keywords :v [{:n 2} {:n 1 :v 1} {:n 3 :v 2}]))))

(run-test test-variation)
