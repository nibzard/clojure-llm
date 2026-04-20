(defn odd_count
  "Given a list of strings, where each string consists of only digits, return a list.
  Each element i of the output should be \"the number of odd elements in the
  string i of the input.\" where all the i's should be replaced by the number
  of odd digits in the i'th string of the input."
  [lst]
  (map (fn [s]
         (let [odd-count (count (filter #(odd? (- (int %) 48)) s))
               template "the number of odd elements in the string i of the input."]
           (clojure.string/replace template #"i" (str odd-count))))
       lst))