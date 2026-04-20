(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"A" 1 "FOO" 2 "bar" 3}
         (uppercase-keywords {:a 1 :foo 2 "bar" 3})))
  (is (= {nil 1 "X" 2}
         (uppercase-keywords {nil 1 :x 2}))))

(run-test test-variation)
