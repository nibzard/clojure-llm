(defn separate_paren_groups
  "Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string."
  [paren_string]
  (let [cleaned (clojure.string/replace paren_string #"\s+" "")]
    (:result
     (reduce (fn [{:keys [balance current result]} c]
               (let [new-balance (if (= c \() (inc balance) (dec balance))
                     new-current (conj current c)]
                 (if (zero? new-balance)
                   {:balance 0
                    :current []
                    :result (conj result (apply str new-current))}
                   {:balance new-balance
                    :current new-current
                    :result result})))
             {:balance 0 :current [] :result []}
             cleaned))))