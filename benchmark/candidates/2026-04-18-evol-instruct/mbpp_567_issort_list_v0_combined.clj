(defn sorted-by-length?
  "Return true if the collection is sorted in nondecreasing order by element length.
  Works with strings, vectors, and nil values (nil counts as length 0).

  Examples:
  (sorted-by-length? [nil \"a\" \"bb\"]) => true
  (sorted-by-length? [\"aa\" \"b\"]) => false
  (sorted-by-length? []) => true"
  [coll]
  (let [len (fn [x] (if (nil? x) 0 (count x)))]
    (every? true?
            (map <=
                 (map len coll)
                 (rest (map len coll))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (sorted-by-length? [nil "a" "bb"])))
  (is (= false (sorted-by-length? ["aa" "b"])))
  (is (= true (sorted-by-length? []))))

(run-test test-variation)
