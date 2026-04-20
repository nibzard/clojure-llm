(defn balanced-from-parts
  "Given a vector of two strings containing only '(' and ')', determine whether
  there exists an order of concatenation that makes a balanced parentheses
  string.

  Return \"Yes\" if either a+b or b+a is balanced, otherwise return \"No\".

  Examples:
  >>> (balanced-from-parts [\"()\" \"(())\"])
  \"Yes\"
  >>> (balanced-from-parts [\")\" \"(\"])
  \"Yes\"
  >>> (balanced-from-parts [\"(\" \")(\"])
  \"No\""
  [parts]
  (letfn [(balanced? [s]
            (zero?
             (reduce (fn [n ch]
                       (let [n' (if (= ch \() (inc n) (dec n))]
                         (if (neg? n') (reduced ::bad) n')))
                     0
                     s)))]
    (if (or (balanced? (str (first parts) (second parts)))
            (balanced? (str (second parts) (first parts))))
      "Yes"
      "No")))