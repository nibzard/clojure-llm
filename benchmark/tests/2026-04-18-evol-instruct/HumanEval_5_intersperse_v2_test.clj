(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (intersperse-right [] :x)))
  (is (= [1 :x 2 :x 3] (intersperse-right [1 2 3] :x)))
  (is (= [\a nil \b] (intersperse-right '(a b) nil))))

(run-test test-variation)
