(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= :d (can-arrange-key {:a 1 :b 2 :c 4 :d 3 :e 5})))
  (is (= nil (can-arrange-key {:x 10 :y 20 :z 30})))
  (is (= :c (can-arrange-key {:a nil :b 1 :c 0}))))

(run-test test-variation)
