(defn move_num
  "	Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [s (or test_str "")
        {:keys [non-digits digits]}
        (reduce (fn [{:keys [non-digits digits]} ch]
                  (if (Character/isDigit ^char ch)
                    {:non-digits non-digits
                     :digits (conj digits ch)}
                    {:non-digits (conj non-digits ch)
                     :digits digits}))
                {:non-digits [] :digits []}
                s)]
    (apply str (concat non-digits digits))))