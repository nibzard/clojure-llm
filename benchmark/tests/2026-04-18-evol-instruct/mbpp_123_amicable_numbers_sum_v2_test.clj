(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[220 284]]
         (doall (amicable_pairs-up-to 300))))
  (is (= [[220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368]]
         (doall (amicable_pairs-up-to 10000)))))

(run-test test-variation)
