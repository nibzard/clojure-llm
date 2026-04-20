(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [nil nil 1 2 3] (stable-sort-by identity [3 nil 2 nil 1])))
  (is (= ["a" "cc" "bbb"] (stable-sort-by count ["bbb" "a" "cc"])))
  (is (= [{:name "Bob" :age 25} {:name "Ada" :age 30} {:name "Eve" :age 30}]
         (stable-sort-by :age [{:name "Ada" :age 30} {:name "Bob" :age 25} {:name "Eve" :age 30}])))

(run-test test-variation)
