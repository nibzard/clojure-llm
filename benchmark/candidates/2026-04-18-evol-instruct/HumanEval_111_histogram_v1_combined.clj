(defn histogram-by-length
  "Given a collection of strings, return a map of the strings with the greatest length
  and the corresponding length.

  If several strings share the maximum length, return all of them.
  Ignore nil values and empty strings.

  Examples:
  >>> (histogram-by-length [\"a\" \"bb\" \"c\" \"dd\"])
  {\"bb\" 2, \"dd\" 2}

  >>> (histogram-by-length [nil \"\" \"cat\" \"ox\" \"horse\" \"dog\"])
  {\"horse\" 5}

  >>> (histogram-by-length [])
  {}"
  [xs]
  (let [valid (filter seq xs)
        groups (group-by count valid)
        m (when (seq groups) (apply max-key key groups))]
    (if m
      (into {} (map (fn [s] [s (count s)]) (val m)))
      {})))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"bb" 2, "dd" 2} (histogram-by-length ["a" "bb" "c" "dd"])))
  (is (= {"horse" 5} (histogram-by-length [nil "" "cat" "ox" "horse" "dog"])))
  (is (= {} (histogram-by-length []))))

(run-test test-variation)
