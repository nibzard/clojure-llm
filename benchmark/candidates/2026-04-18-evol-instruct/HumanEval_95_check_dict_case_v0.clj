(defn check-set-case
  "Given a set, return true if all string elements are all lower case
   or all upper case, else return false. Return false for an empty set.
   Non-string elements make the result false.

   Examples:
   >>> (check-set-case #{\"a\" \"b\" \"c\"})
   true
   >>> (check-set-case #{\"A\" \"B\" \"C\"})
   true
   >>> (check-set-case #{\"a\" \"B\"})
   false
   >>> (check-set-case #{\"a\" 1 \"b\"})
   false
   >>> (check-set-case #{})
   false"
  [s]
  (let [xs (seq s)]
    (and xs
         (every? string? xs)
         (let [all-lower? (every? #(= % (clojure.string/lower-case %)) xs)
               all-upper? (every? #(= % (clojure.string/upper-case %)) xs)]
           (or all-lower? all-upper?)))))