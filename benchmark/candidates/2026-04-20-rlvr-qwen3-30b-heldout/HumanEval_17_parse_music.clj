(defn parse_music
  " Input to this function is a string representing musical notes in a special ASCII format.
  Your task is to parse this string and return list of integers corresponding to how many beats does each
  not last.
  Here is a legend:
  'o' - whole note, lasts four beats
  'o|' - half note, lasts two beats
  '.|' - quater note, lasts one beat
  >>> (parse_music \"o o| .| o| o| .| .| .| .| o o\")
  [4 2 1 2 2 1 1 1 1 4 4]"
  [music_string]
  (mapv (fn [note]
          (cond
            (= note "o") 4
            (= note "o|") 2
            (= note ".|") 1
            :else 0))
        (clojure.string/split (or music_string "") #"\s+")))