(defn find_substring
  "	Write a function to check if a string is present as a substring in a given list of string values."
  [str1 sub_str]
  (let [s (or str1 "")
        sub (or sub_str "")]
    (boolean (re-find (re-pattern (java.util.regex.Pattern/quote sub)) s))))