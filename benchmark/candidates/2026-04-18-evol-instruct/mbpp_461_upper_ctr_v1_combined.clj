(defn upper-ctr-nested
  "Count the uppercase characters in a nested collection of strings.
  
  Examples:
  (upper-ctr-nested [\"Ab\" [\"Cde\" nil] \"FG\"]) ;=> 4
  (upper-ctr-nested nil) ;=> 0
  (upper-ctr-nested [\"abc\" [\"DEF\" [\"G\"]]]) ;=> 4"
  [x]
  (letfn [(count-in-item [v]
            (cond
              (nil? v) 0
              (string? v) (count (filter #(Character/isUpperCase ^char %) v))
              (coll? v) (reduce + 0 (map count-in-item v))
              :else 0))]
    (count-in-item x)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (upper-ctr-nested ["Ab" ["Cde" nil] "FG"]))))

(run-test test-variation)
