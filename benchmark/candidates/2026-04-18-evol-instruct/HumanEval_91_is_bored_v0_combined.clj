(defn count-vowels-in-prefixes
  "Given a collection of strings, count how many prefixes across all strings contain at least one vowel.

  A prefix is any initial segment of a string, including the whole string.
  Count only prefixes that are non-empty and contain at least one of the vowels a, e, i, o, or u.

  Examples:
  >>> (count-vowels-in-prefixes [\"bc\" \"sky\"])
  0
  >>> (count-vowels-in-prefixes [\"cat\" \"rhythm\" \"apple\"])
  9
  >>> (count-vowels-in-prefixes nil)
  0"
  [strings]
  (let [vowels #{\a \e \i \o \u}]
    (reduce
      (fn [acc s]
        (if (string? s)
          (+ acc
             (count
               (filter
                 #(some vowels %)
                 (rest (reductions str "" s)))))
          acc))
      0
      (or strings []))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (count-vowels-in-prefixes ["bc" "sky"])))
  (is (= 9 (count-vowels-in-prefixes ["cat" "rhythm" "apple"])))
  (is (= 0 (count-vowels-in-prefixes nil))))

(run-test test-variation)
