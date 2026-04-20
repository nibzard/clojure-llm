(defn same_keys
  "Check whether two maps contain the same set of keys, ignoring values.
  Nil is treated as an empty map.

  >>> (same_keys {:a 1 :b 2} {:b 9 :a 0})
  true
  >>> (same_keys {:a 1 :b 2} {:a 1 :b 2 :c 3})
  false
  >>> (same_keys nil {})
  true
  >>> (same_keys {:a 1 :b nil} {:b 4 :a 7})
  true"
  [m0 m1]
  (= (set (keys (or m0 {})))
     (set (keys (or m1 {})))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same_keys {:a 1 :b 2} {:b 9 :a 0})))
  (is (= false (same_keys {:a 1 :b 2} {:a 1 :b 2 :c 3})))
  (is (= true (same_keys nil {})))
  (is (= true (same_keys {:a 1 :b nil} {:b 4 :a 7}))))

(run-test test-variation)
