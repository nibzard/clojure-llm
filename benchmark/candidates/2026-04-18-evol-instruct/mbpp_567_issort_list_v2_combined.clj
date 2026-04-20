(defn sorted-by-length?
  "Return true if the given collection of strings is sorted in nondecreasing order by length.
  
  Examples:
  (sorted-by-length? [\"a\" \"to\" \"cat\"]) => true
  (sorted-by-length? [\"cat\" \"a\" \"to\"]) => false
  (sorted-by-length? nil) => true"
  [coll]
  (or (nil? coll)
      (apply <= (map count coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (sorted-by-length? ["a" "to" "cat"]))))

(run-test test-variation)
