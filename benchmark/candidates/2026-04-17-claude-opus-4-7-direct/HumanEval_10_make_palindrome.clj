(defn make_palindrome
  "Find the shortest palindrome that begins with a supplied string.
  Algorithm idea is simple:
  - Find the longest postfix of supplied string that is a palindrome.
  - Append to the end of the string reverse of a string prefix that comes before the palindromic suffix."
  [string]
  (if (empty? string)
    ""
    (let [len (count string)
          ;; Find the length of the longest palindromic suffix
          suffix-len (loop [i 0]
                       (if (or (= i len)
                               (let [end (- len i)]
                                 (not= (subs string i) 
                                        (apply str (reverse (subs string 0 end)))))))
                         i
                         (recur (inc i))))]
      ;; Append the reverse of the prefix to the original string
      (str string (apply str (reverse (subs string 0 (- len suffix-len))))))))