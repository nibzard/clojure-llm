(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[220 284]] (vec (take 1 (lazy-amicable-pairs 300)))))
  (is (= [[220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368]]
         (into [] (lazy-amicable-pairs 10000)))))

(run-test test-variation)
