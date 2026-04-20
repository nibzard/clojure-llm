(defn match_parens
  "You are given a list of two strings, both strings consist of open
  parentheses '(' or close parentheses ')' only.
  Your job is to check if it is possible to concatenate the two strings in
  some order, that the resulting string will be good.
  A string S is considered to be good if and only if all parentheses in S
  are balanced. For example: the string '(())()' is good, while the string
  '())' is not.
  Return 'Yes' if there's a way to make a good string, and return 'No' otherwise.
  Examples:
  >>> (match_parens [\"()(\" \")\"])
  \"Yes\"
  >>> (match_parens [\")\" \")\"])
  \"No\""
  [lst]
  (letfn [(good? [s]
            (let [result (reduce (fn [bal ch]
                                   (when (some? bal)
                                     (let [nbal (if (= ch \() (inc bal) (dec bal))]
                                       (when (not (neg? nbal)) nbal))))
                                 0
                                 s)]
              (= result 0)))]
    (let [[a b] lst]
      (if (or (good? (str a b))
              (good? (str b a)))
        "Yes"
        "No"))))