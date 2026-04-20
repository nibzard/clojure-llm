(defn merge-dictionaries-variadic
  "Merge any number of maps into one map.

  Later maps override earlier ones for duplicate keys.

  Examples:
  (merge-dictionaries-variadic {:a 1} {:b 2} {:a 3})
  => {:a 3, :b 2}

  (merge-dictionaries-variadic)
  => {}"
  [& maps]
  (apply merge maps))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 3, :b 2} (merge-dictionaries-variadic {:a 1} {:b 2} {:a 3})))
  (is (= {} (merge-dictionaries-variadic)))
  (is (= {:x 10 :y 20} (merge-dictionaries-variadic nil {:x 10} {:y 20}))))

(run-test test-variation)
