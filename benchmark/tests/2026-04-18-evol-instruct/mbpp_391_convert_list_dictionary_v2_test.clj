(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a {:b 1, :c 2}, :d 3}
         (zip-to-deep-map [[:a :b] [:a :c] [:d]] [1 2 3]))))

(run-test test-variation)
