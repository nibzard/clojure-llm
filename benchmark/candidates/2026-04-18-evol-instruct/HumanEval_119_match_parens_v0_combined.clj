(defn longest-balanced-prefix
  "Given a string containing only '(' and ')', return the length of the longest
  prefix that is a balanced parentheses string.

  A prefix is any initial substring of the original string. A prefix is balanced
  if it never has more ')' than '(' at any point, and ends with equal numbers of
  '(' and ')'.

  Return 0 if no balanced prefix exists.

  Examples:
  >>> (longest-balanced-prefix \"()())\")
  4
  >>> (longest-balanced-prefix \"((()))xyz\")
  6
  >>> (longest-balanced-prefix \"(\")
  0"
  [s]
  (loop [chars (seq s)
         depth 0
         best 0
         idx 0]
    (if-let [c (first chars)]
      (let [depth' (case c
                     \( (inc depth)
                     \) (dec depth)
                     depth)
            best' (if (zero? depth') idx best)]
        (if (neg? depth')
          best
          (recur (next chars) depth' best' (inc idx))))
      best)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (longest-balanced-prefix "()())")))

(run-test test-variation)
