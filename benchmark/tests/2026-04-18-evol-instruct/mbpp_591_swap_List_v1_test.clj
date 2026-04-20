(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 3 1] (swap-extremes [1 2 3 4])))
  (is (= [:c :b :a] (swap-extremes '(:a :b :c))))
  (is (= [42] (swap-extremes [42])))
  (is (= [] (swap-extremes nil))))

(run-test test-variation)
