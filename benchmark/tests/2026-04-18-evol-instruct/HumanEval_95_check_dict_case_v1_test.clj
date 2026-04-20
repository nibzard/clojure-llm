(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= :lower (normalize-key-case {"a" 1, "b" 2})))
  (is (= :upper (normalize-key-case {"A" 1, "B" 2})))
  (is (= :mixed (normalize-key-case {"a" 1, "B" 2})))
  (is (= :invalid (normalize-key-case {"a" 1, 8 2})))
  (is (= :empty (normalize-key-case {}))))

(run-test test-variation)
