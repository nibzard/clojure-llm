(defn freq_count_no_nil
  "Return a frequency map of the items in coll, ignoring nil values.

  Examples:
  (freq_count_no_nil [:a :b :a nil :b :c nil])
  ;;=> {:a 2, :b 2, :c 1}

  Works with any sequential collection, including lazy sequences."
  [coll]
  (reduce (fn [m x]
            (if (nil? x)
              m
              (update m x (fnil inc 0))))
          {}
          coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 2, :b 2, :c 1}
         (freq_count_no_nil [:a :b :a nil :b :c nil]))))

(run-test test-variation)
