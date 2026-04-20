(defn merge-nested-maps
  "Return a nested map formed by recursively merging two maps.

  For keys present in both maps:
  - If both values are maps, merge them recursively.
  - Otherwise, the value from the second map wins.

  Examples:
  (merge-nested-maps {:a 1 :b {:c 2}} {:b {:d 3} :e 4})
  ;; => {:a 1, :b {:c 2, :d 3}, :e 4}

  (merge-nested-maps nil {:a 1})
  ;; => {:a 1}"
  [m1 m2]
  (letfn [(merge* [a b]
            (cond
              (nil? a) b
              (nil? b) a
              (and (map? a) (map? b))
              (merge-with merge* a b)
              :else b))]
    (merge* m1 m2)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :b {:c 2 :d 3} :e 4}
         (merge-nested-maps {:a 1 :b {:c 2}} {:b {:d 3} :e 4})))
  (is (= {:a 1} (merge-nested-maps nil {:a 1})))
  (is (= {:a 2 :b 3}
         (merge-nested-maps {:a 1 :b 2} {:a 2}))))

(run-test test-variation)
