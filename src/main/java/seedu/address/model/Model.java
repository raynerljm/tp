package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    /**
     * @{code Predicate} that only evaluates to true if the meeting is today or in the future
     */
    Predicate<Meeting> PREDICATE_SHOW_UPCOMING_MEETINGS = meeting -> {
        return meeting.isUpcoming();
    };

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Returns an unmodifiable view of the filtered client list
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    ObservableList<Client> getSortedClientList();

    void updateSortedClientList(Comparator<Client> comparator);

    Index getDisplayedClientIndex();

    void updateDisplayedClientIndex(Index index);

    /**
     * Adds the given meeting.
     * {@code meeting} must not overlap with existing meetings.
     */
    void addMeeting(Meeting meeting);

    /**
     * Deletes the given meeting.
     * The meeting must exist in the address book.
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting timing of {@code editedMeeting} must not be overlapping with
     * another existing meeting in the address book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /**
     * Sorts the meetings by descending order.
     */
    void sortMeetings();

    /**
     * Returns true if there is a meeting that overlaps with {@code meeting} in the address book.
     */
    boolean isOverlapping(Meeting meeting);

    /**
     * Returns true if there is a meeting that overlaps with {@code meeting} in the addres book except the
     * specified meeting.
     */
    boolean isOverlappingExcept(Meeting meeting, Meeting exceptedMeeting);

    /**
     * Returns an unmodifiable view of the filtered meeting list
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Returns whether all or only upcoming meetings are displayed.
     */
    boolean isShowAllMeetings();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate, boolean isShowAll);
}

